# Sales Manager Product Service

Microsservice for product management of the sales-manager architecture

* **URL**\
/product

* **Method:**\
 `POST`
 
 *  **Request Body**
    ```json
    {
      "name": "Televisão 4K",
      "quantity": 10,
      "unitaryValue": 2200
    }
    ```
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    {
      "_id": "5f7cdfb273e86e129ea6efa9",
      "name": "Televisão 4K",
      "unitaryValue": 2200,
      "quantity": 10,
      "creationDate": "2020-10-06T18:20:50.0812977",
      "updateDate": "2020-10-06T18:20:50.0812977"
    }
    ```
    
* **Error Response:**

    * **Code:** 400 <br />
        **Content:** 
    ```json
    {
      "timestamp": "2020-10-06T18:21:07.422669",
      "status": 400,
      "message": "Invalid null or blank field"
    }
    ```
  
* **URL**\
/product/availability/{productId}

* **Method:**\
 `GET`
 
 *  **URL Params**
 
    **Required:**
    
    `productId={String}`
    
    `requestedQuantity ={Integer}`
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    true
    ```
   
* **URL**\
/product/unitaryValue/{productId}

* **Method:**\
 `GET`
 
 *  **URL Params**
 
    **Required:**
    
    `productId={String}`
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    2200
    ```
  
 * **Architecture:**
 
    ![Alt text](https://user-images.githubusercontent.com/51386403/95261158-45436900-0800-11eb-9b10-ec7bfe7cd371.png "Architecture")
    * 1 - Will receive an order and check if stock is avaliable
    * 2 - If has stock, will create the order and persist on MongoDB with status ***PENDING***;
    * 3 - The persisted order will be produced on ***NEW_ORDER*** Kafka topic;
    * 4 - Will listen to the topic and check if the customer has available balance;
    * 5 - Will produce a message on ***ORDER_STATUS_CHANGE*** Kafka topic updating the order status (***FINISHED*** or ***CANCELLED***);
    * 6 - Will listen to the topic and update the order status on MongoDB;
    * 7 - Will update the product stock (if product status is ***FINISHED***.
    
    
    
