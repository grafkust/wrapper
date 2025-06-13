import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
    const [inputData, setInputData] = useState({});
    const [resultData, setResultData] = useState({});
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    // Загружаем входные данные при запуске
    useEffect(() => {
        fetchInputData();
        fetchResultData();
    }, []);

    const fetchInputData = async () => {
        try {
            setLoading(true);
            const response = await axios.get('/api/v1/spreadsheets/wind/input');
            setInputData(response.data);
            setError(null);
        } catch (err) {
            setError('Ошибка загрузки входных данных: ' + err.message);
        } finally {
            setLoading(false);
        }
    };

    const fetchResultData = async () => {
        try {
            const response = await axios.get('/api/v1/spreadsheets/wind/result');
            setResultData(response.data);
        } catch (err) {
            console.error('Ошибка загрузки результатов:', err);
        }
    };

    const handleInputChange = (key, value) => {
        setInputData(prev => ({
            ...prev,
            [key]: value === '' ? 0 : parseFloat(value) || 0
        }));
    };

    const updateData = async () => {
        try {
            setLoading(true);

            // Формируем данные для отправки в формате, который ожидает API
            const values = [
                [inputData.length?.toString().replace('.', ',') || '0'],
                [inputData.rise?.toString().replace('.', ',') || '0'],
                [inputData.height?.toString().replace('.', ',') || '0'],
                [inputData.safety?.toString().replace('.', ',') || '0'],
                [inputData.pressure?.toString().replace('.', ',') || '0'],
                [inputData.coefA?.toString().replace('.', ',') || '0'],
                [inputData.coefB?.toString().replace('.', ',') || '0'],
                [inputData.coefC?.toString().replace('.', ',') || '0']
            ];

            await axios.put('/api/v1/spreadsheets/wind/update', { values });

            // Обновляем результаты после отправки
            setTimeout(() => {
                fetchResultData();
            }, 1000);

            setError(null);
        } catch (err) {
            setError('Ошибка обновления данных: ' + err.message);
        } finally {
            setLoading(false);
        }
    };

    if (loading && Object.keys(inputData).length === 0) {
        return <div className="loading">Загрузка...</div>;
    }

    return (
        <div className="App">
            <header className="header">
                <h1>Расчет ветровой нагрузки имени Александра КОРЕНЦА</h1>
            </header>

            <div className="content">
                <div className="sidebar">
                    <nav>
                        <ul>
                            <li className="active">Ветер</li>
                            <li>Снег 1</li>
                            <li>Снег 2</li>
                        </ul>
                    </nav>
                </div>

                <div className="main-content">
                    {error && <div className="error">{error}</div>}

                    <div className="panels">
                        {/* Панель ввода данных */}
                        <div className="panel input-panel">
                            <h2>Входные параметры</h2>
                            <div className="input-grid">
                                <div className="input-group">
                                    <label>Длина (l), м:</label>
                                    <input
                                        type="number"
                                        step="0.1"
                                        value={inputData.length !== undefined ? inputData.length : ''}
                                        onChange={(e) => handleInputChange('length', e.target.value)}
                                    />
                                </div>

                                <div className="input-group">
                                    <label>"Эф" (f), м:</label>
                                    <input
                                        type="number"
                                        step="0.1"
                                        value={inputData.rise || ''}
                                        onChange={(e) => handleInputChange('rise', e.target.value)}
                                    />
                                </div>

                                <div className="input-group">
                                    <label>Высота (h1), м:</label>
                                    <input
                                        type="number"
                                        step="0.1"
                                        value={inputData.height || ''}
                                        onChange={(e) => handleInputChange('height', e.target.value)}
                                    />
                                </div>

                                <div className="input-group">
                                    <label>Коэффициент (k):</label>
                                    <input
                                        type="number"
                                        step="0.1"
                                        value={inputData.safety || ''}
                                        onChange={(e) => handleInputChange('safety', e.target.value)}
                                    />
                                </div>

                                <div className="input-group">
                                    <label>Дабл-Ю (w0):</label>
                                    <input
                                        type="number"
                                        step="0.001"
                                        value={inputData.pressure || ''}
                                        onChange={(e) => handleInputChange('pressure', e.target.value)}
                                    />
                                </div>

                                <div className="input-group">
                                    <label>Коэффициент A (coefA):</label>
                                    <input
                                        type="number"
                                        step="0.1"
                                        value={inputData.coefA || ''}
                                        onChange={(e) => handleInputChange('coefA', e.target.value)}
                                    />
                                </div>

                                <div className="input-group">
                                    <label>Коэффициент B (coefB):</label>
                                    <input
                                        type="number"
                                        step="0.1"
                                        value={inputData.coefB || ''}
                                        onChange={(e) => handleInputChange('coefB', e.target.value)}
                                    />
                                </div>

                                <div className="input-group">
                                    <label>Коэффициент C (coefC):</label>
                                    <input
                                        type="number"
                                        step="0.1"
                                        value={inputData.coefC || ''}
                                        onChange={(e) => handleInputChange('coefC', e.target.value)}
                                    />
                                </div>
                            </div>

                            <button
                                className="calculate-btn"
                                onClick={updateData}
                                disabled={loading}
                            >
                                {loading ? 'Расчет...' : 'Рассчитать'}
                            </button>
                        </div>

                        {/* Панель результатов */}
                        <div className="panel result-panel">
                            <h2>Результаты расчета</h2>
                            <div className="results-table">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>21</th><th>19,4</th><th>17,6</th><th>15,8</th><th>14</th><th>12,2</th><th>10,4</th>
                                        <th>8,6</th><th>6,8</th><th>5</th><th>3,2</th><th>1,4</th><th>0</th><th>1,4</th>
                                        <th>3,2</th><th>5</th><th>6,8</th><th>8,6</th><th>10,4</th><th>12,2</th><th>14</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        {Array.from({length: 21}, (_, i) => (
                                            <td key={i}>{resultData[`res_${i+1}`]?.toFixed(3) || '0.000'}</td>
                                        ))}
                                    </tr>
                                    <tr>
                                        {Array.from({length: 21}, (_, i) => (
                                            <td key={i+21}>{resultData[`res_${i+22}`]?.toFixed(3) || '0.000'}</td>
                                        ))}
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default App;