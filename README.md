### The File Generator
_______________________
### **About**

- This is an application (web-service) that allows one to generate and download file(excel for now) 
based on dynamic no of columns at runtime and certain configuration values such as prefix for email,phone and so on.
- This is the backend service available at the endpoint /api/v1/fileGenerator/download which receives 
  the payload that differs from the user to user at run time. Payload example is as follows: 
  ```json
   {
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
   }
  

### Challenges
_____________
- Since, the number of items inside the "**headers**" payload dynamically 
 grow and shrink based on the user's requirements, it is very 
 hard to determine and return the exact type or the exact format 
 while filling the excel file. For instance, when user adds address as the name of 
 of the header column, not sure how to determine the value for this column. **Furthermore**, using ```if else``` is cumbersome and it never meets the requirements.
 Another example, could be let us say user entered the completely uncommon header, then addressing with if else also becomes almost not feasible and type of data would just look too random and additionally not meaningful even though it will contain some type of random data.
<br/>
<br/>

### Further work 
____________________
- Work can be done with slight modification to generate csv file. In additon to this, it can also be made for pdf file generation.
- Is there any proper work that can address the above problem ? 
- Can we make some logic and implement it in a method to accommodate most of the data types ? It would be nice if it 
  returns some meaningful formatted values for columns other than those explicitly handled.
<br>
<br>

### Postmand Request and Response Example
________________________________________
  ![Postman Request Response Example Screenshot](https://github.com/badripaudel77/the-file-generator/issues/1)
<br>

1. <span>In the screenshot example above, inside of the headers key, we have other nested keys, namely, email, phone, name, address, dummy and these are essentially excel files' header name.</span>
2. This key will be as many as we put and headers will be generated accordingly.
3. Inside of the each key, we have other key such as type, prefix, postfix, these are fundamentally, extra configurations for the value of the header to be generated. 
  For instance, if email has following structure : 
  ```json
 {
  "email" : {
      "type" : "email",
      "prefix": "cse-",
      "postfix" : "ubc.com",
      "value": "some_value"
   }
}
```
In such case, one of the example emails which would be generated is cse-111222@ubc.com, where 111222 will be some randomly generated value. If we include value as well, it will just be appended to the prefix.

#### Example CURL request :  
```json
curl -X POST http://localhost:8080/api/v1/fileGenerator/download \
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
