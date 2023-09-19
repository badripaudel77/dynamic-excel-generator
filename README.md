### the-file-generator
- This is an application (web-service) that allows one to generate and download file(excel for now) 
based on dynamic no of columns at runtime and certain configuration values such as prefix for email,phone and so on.

Example CURL request : 
``` curl -X POST http://localhost:8080/api/v1/fileGenerator/download \
-H "Content-Type: application/json" \
-d '{
    "fileType": "",
    "headers": {
        "email": {
            "type": "email",
            "prefix": "abc",
            "postfix": "abc.io",
            "value": "demoemail"
        },
        "phone": {
            "type": "phone",
            "prefix": "+977",
            "value": "9867612345"
        },
        "name": {
            "type": "name",
            "prefix": "John",
            "postfix": "Doe",
            "value": "Demo"
        },
        "address": {
            "type": "string",
            "prefix": "BGL-",
            "value": "Nepal"
        },
        "dummy": {
            "type": "",
            "prefix": "",
            "postfix": ""
        }
    },
    "noOfRecords": 20
}'
```
