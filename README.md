
## Profile-service

Developed a profile service using spring boot latest version 3.0.3 and mysql 8.0.7(Compatible with spring boot latest version) as database.
Utilized the service with spring framework.

Created four tables in profile_service database :  
- user table where user records(id,firstName, lastName, NRC) are stored
- contact_info table where user records(Type of Contacts , Contacts) are stored
- contact_type table where what types of user contacts (Email, Phone) are stored
- address table where user addresses (address, townshipOrCity, district, state, postalCode) are stored

<img width="419" alt="profile_service_ERD" src="https://user-images.githubusercontent.com/126815203/222920446-abdd2efd-f1c2-41c9-9ab2-b183dcfb4fb2.png">
        

Api(s) called CRUD operations are applied in profile service :
- Creating, updating, and deleting user records
- Getting user info lists ( features like pagination and searching by User Id are applied )
- Creating and updating addresses, contacts of each user

Generating API documentation with Swagger.

<img width="710" alt="image" src="https://user-images.githubusercontent.com/126815203/222920623-6f3094a2-779f-447f-8310-0c0b1aa6653f.png">


