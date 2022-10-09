# Technical Test Java

port yang di set pada application.yml adalah 8090

Url pengetesan lokal:
```
http://localhost:8090
```

# Service dan body
Add Job Master terlebih dahulu sebelum add Employee

#Job
1. Add Job (POST)
```
http://localhost:8090/job
```

```
{
    "jobCode" : "00A",
    "jobDesc" : "Job A"
}
```

2. Get All Job (GET)
```
http://localhost:8090/job?page=0&size=10
```


# Employee
1. Add Employee (POST)
```
http://localhost:8090/employee
```

```
{
    "fullName" : "TEST",
    "gender" : "L",
    "religion" : "ISLAM",
    "email" : "test@mail.com",
    "age" : "24",
    "birthDate" : "1998-12-06",
    "jobCode" : "00A",
    "insertBy" : "IT"
}
```

2. Get All Employee (GET)
```
http://localhost:8090/employee?page=0&size=10
```

3. Edit Employee (PUT)
```
http://localhost:8090/employee
```

```
{
    "employeeId" : "20220002",
    "fullName" : "TEST EDIT2",
    "gender" : "L",
    "religion" : "BUDHA",
    "email" : "testedit2@mail.com",
    "age" : "21",
    "birthDate" : "1997-09-06",
    "jobCode" : "124",
    "updateBy" : "IT UPDATE2",
    "deleted" : 1
}
```