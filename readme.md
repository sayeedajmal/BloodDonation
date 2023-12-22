# Setup MySql

    - db name  : BloodBank
    - username : BloodBank
    - password : BlookBank

    sudo mysql -u root
    create database BloodBank;
    CREATE USER 'BloodBank'@'localhost' IDENTIFIED BY 'BloodBank';

# Documentation Of End Points

## Donor

- Base URL: `/api/v1/donor`

  - **POST** `createDonor`

    - @Require Donor
    - @Return HttpStatus.Code

  - **GET** `showDonor`

    - @Return HttpStatus with Donor

  - **GET** `/{donorId}`

    - @Param `donorId`: Integer, the unique identifier of the donor
    - @Return HttpStatus

  - **DELETE** `/{donorId}`

    - @Param `donorId`: Integer, the unique identifier of the donor
    - @Return HttpStatus

  - **PATCH** `updateDonor`
    - @Require Donor
    - @Return HttpStatus

## AppointController

- Base URL: `/api/v1/appointment`

  - **POST** `createAppointment`

    - @Require Appointment, Integer donorId
    - @Return HttpStatus

  - **GET** `showAppointment`

    - @Return HttpStatus With Appointment

  - **GET** `/{appointmentId}`

    - @Param `appointmentId`: Integer, the unique identifier of the appointment
    - @Return HttpStatus With Appointment

  - **DELETE** `/{appointmentId}`

    - @Param `appointmentId`: Integer, the unique identifier of the appointment
    - @Return HttpStatus

  - **PATCH** `updateAppointment`
    - @Require Appointment, Integer id
    - @Return HttpStatus

## MedicalHistController

- Base URL: `/api/v1/medicalHistory`

  - **POST** `createHistory`

    - @Require MedicalHistory, Integer donorId
    - @Return HttpStatus

  - **GET** `showHistory`

    - @Return HttpStatus With MedicalHistory

  - **GET** `/{historyId}`

    - @Param `historyId`: Integer, the unique identifier of the medical history
    - @Return HttpStatus

  - **GET** `findByDonor/{donorId}`

    - @Param `donorId`: Integer, the unique identifier of the donor
    - @Return HttpStatus

  - **DELETE** `/{historyId}`

    - @Param `historyId`: Integer, the unique identifier of the medical history
    - @Return HttpStatus

  - **PATCH** `updateHistory`
    - @Require MedicalHistory
    - @Return HttpStatus

## StaffController

- Base URL: `/api/v1/Staff`

  - **POST** `createStaff`

    - @Require Staff
    - @Return HttpStatus

  - **GET** `showStaff`

    - @Return HttpStatus With Staff

  - **GET** `/{staffId}`

    - @Param `staffId`: Integer, the unique identifier of the staff
    - @Return HttpStatus

  - **DELETE** `/{staffId}`

    - @Param `staffId`: Integer, the unique identifier of the staff
    - @Return HttpStatus

  - **PATCH** `updateStaff`
    - @Require Staff
    - @Return HttpStatus
