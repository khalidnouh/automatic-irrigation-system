
# To Run 

	> mvn clean install
	> java -jar target/irrigationsystem-0.0.1.jar

# To Run Test Cases 
	
	> mvn test
	
# APIs Examples :

# Get a registered plot by id API
	> GET - http://localhost:8282/api/plot/get/{id}

# Get all registered plot API
	> GET - http://localhost:8282/api/plot/get

# Register a plot  API
	> POST - http://localhost:8282/api/plot/save
	{
    "sensor":{ "id":1},
    "irrigationPeriodDTOS": [
        {
            "amount": 1000,
            "startDate": "26/5/2023",
            "endDate": "30/5/2023",
            "irrigateSchedulerInDays": 1
        }
    ]
}

# Save Sensor API
	>POST - http://localhost:8282/api/sensor/save
			{
		"sensorCode": "001",
		"sensorApi": "http://localhost:8282/api/sensor/attt",
		"secretKey": "6699-845566",
		"maxAttempt": 3 
	}

# Get Sensor By Id API
	>GET - http://localhost:8282/api/sensor/get/{id}
	
# Get All Sensor API
	>GET - http://localhost:8282/api/sensor/get
