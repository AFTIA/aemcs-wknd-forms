CREATE TABLE Lead (
   lead_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   phone_number VARCHAR(255),
   email_address VARCHAR(255),
   birth_date VARCHAR(255),
   street_address VARCHAR(255),
   city VARCHAR(255),
   postal_code VARCHAR(255),
   province VARCHAR(255),
   country VARCHAR(255),
   PRIMARY KEY (lead_id)
)