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

 *  **Path Variables**

    `productId={String}`
 *  **URL Params**
 
    **Required:**
    
    `requestedQuantity={Integer}`
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
 
 *  **Path Variables**
    
    `productId={String}`
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    2200
    ```

* **URL**\
/product

* **Method:**\
 `GET`

 * **Success Response:**

    * **Code:** 200 <br />
        **Content:**
    ```json
    [
      {
        "_id": "5f7cdfb273e86e129ea6efa9",
        "name": "Televisão 4K",
        "unitaryValue": 2200,
        "quantity": 10,
        "creationDate": "2020-10-06T18:20:50.0812977",
        "updateDate": "2020-10-06T18:20:50.0812977"
      }
    ]
    ```
  
* **URL**\
/product/subtractQuantity/{productId}

* **Method:**\
 `PUT`

 *  **Path Variables**

    `productId={String}`
 *  **URL Params**
 
    **Required:**
     
    `quantity={Integer}`
 * **Success Response:**
 
    * **Code:** 200 <br />
        **Content:**
    ```json
    {
      "_id": "5f7cdfb273e86e129ea6efa9",
      "name": "Televisão 4K",
      "unitaryValue": 2200,
      "quantity": 9,
      "creationDate": "2020-10-06T18:20:50.081",
      "updateDate": "2020-10-06T18:49:11.1654678"
    }
    ```
  
 * **Architecture:**
 
    ![Alt text](https://user-images.githubusercontent.com/51386403/95694559-0ef35880-0c09-11eb-9667-9ae838b4d40f.png "Architecture")
    * 1 - Will receive an order and check if stock is available;
    * 2 - If has stock, will create the order and persist on MongoDB with status ***PROCESSING_PAYMENT***;
    * 3 - The persisted order will be produced on ***NEW_ORDER*** Kafka topic;
    * 4 - Will listen to the topic and check if the customer has available balance;
    * 5 - Will produce a message on ***ORDER_STATUS_CHANGE*** Kafka topic updating the order status (***APPROVED*** or ***CANCELLED***);
    * 6 - Will listen to the topic, update the order status to ***PREPARING_FOR_SHIPPING*** (if order was ***APPROVED***)  and produce a message with the changes in the order;
    * 7 - Will listen to the topic and update the order status on MongoDB;
    * 8 - Will update the product stock (if order was ***APPROVED***);
    * 9 - Occasionally will produce messages as the order status changes and persist the changes on MongoDB.