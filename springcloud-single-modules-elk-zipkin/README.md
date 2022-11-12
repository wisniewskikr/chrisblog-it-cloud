Endpoint:
- http://localhost:8761

Launch:
- mvn -f ./service-discovery spring-boot:run
- mvn -f ./service-config spring-boot:run
- mvn -f ./service-helloworld-1 spring-boot:run
- mvn -f ./service-helloworld-2 spring-boot:run
- mvn -f ./service-gateway spring-boot:run

Ports:
- service-discovery: 8761
- service-config: 8888
- service-helloworld-1: 8080
- service-helloworld-2: 8081
- service-gateway: 8762
- service-traking: 9411
- service-logging:

You can start custom service by:
- Custom Service
- Discovery Service
- Gateway Service

Zipkin:
- download and install otp
- download and install rabbitmq (as administrator)
- use command: rabbitmq-plugins enable rabbitmq_management
- check rabbitmq: http://localhost:15672
- download zipkin: curl -sSL https://zipkin.io/quickstart.sh | bash -s
- start zipkin (GIT bash) with: RABBIT_URI=amqp://localhost java -jar zipkin.jar

OTP

Link: https://www.erlang.org/downloads

RABBITMQ

Link: https://www.rabbitmq.com/download.html

ZIPKIN

Link: https://zipkin.io/pages/quickstart.html


EKL

Elasticsearch:
- Download: https://www.elastic.co/downloads/elasticsearch
- Run: bin/elasticsearch.bat
- URL: http://localhost:9200

Kibana:
- Download: https://www.elastic.co/downloads/kibana
- Run: bin/kibana.bat
- URL: http://localhost:5601
- Kibana -> Dev Tools

PUT /javatechie
{
  "settings": {
  "index": {
    "number_of_shards" : 3,
    "number_of_replicas" : 2
  }
  }
}

POST /javatechie/default
{
  "name": "event_processing",
  "instructor": {
    "firstName": "java",
    "lastName": "techie"
  }
}

- Kibana -> Management - Index Pattern -> Create Index Pattern -> Pattern "javatechie*" -> "I don't want to use the Time Filter"
- Kibana -> Discover

Logstash:
- Download: https://www.elastic.co/downloads/logstash
- Create bin/logstash.conf

input {
	file {
		path => "C:\IT\logs\springcloud-simple-modules.log"
		start_position => "beginning"
	}
}

output {
	
	stdout {
		codec => rubydebug
	}
	
	elasticsearch {
		hosts => ["localhost:9200"]
		index => "javatechie-%{+yyyy.MM.dd}"
	}
	
}

- Run: bin/logstash.bat -f logstash.conf

