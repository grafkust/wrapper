## Instruction
- Создать в корне проекта файл `serviceAccountCredentials.json` содержащий креды к service account google. Создается аккаунт через [Google Cloude](https://console.cloud.google.com/)  
```json
{  
  "type":         "service_account",  
  "project_id":   "wrapper",  
  "private_key":  "12345",  
  "client_email": "user@gmail.com",  
  "client_id":    "12345"  
}
```
- Создать в корне проекта файл `.env` 
```
//uuid каждого листа в google sheet
WIND_SHEET_ID = uuid  
SNOW_1_SHEET_ID = uuid  
SNOW_2_SHEET_ID = uuid  
   
API_KEY = key  //ключ доступа к API google sheet
```
- Запустить основное `java` приложение
- Из папки `frontend/` запустить фронт приложение командой `npm start` 
