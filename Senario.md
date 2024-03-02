# Donor Entity

Purpose: Represents information about blood donors.
Attributes:
donorId: Unique identifier for each donor.
firstName and lastName: First and last names of the donor.
dateOfBirth: Date of birth of the donor.
BloodType: Blood group of the donor (using a BloodType enum).
contactNumber: Contact number of the donor.
email: Email address of the donor.
city: city of the donor.
lastDonationDate: Date of the donor's last blood donation.

# Donation Entity:

Purpose: Represents information about blood donations.
Attributes:
donationId: Unique identifier for each donation.
donor: Reference to the donor who made the donation.
donationDate: Date of the donation.
donationLocation: Location where the donation took place.
donationStatus: Status of the donation (using a DonationStatus enum).
bloodType: Blood type of the donated blood (using a BloodType enum).
quantity: Quantity of blood donated.

# BloodBank Entity:

Purpose: Represents information about blood banks.
Attributes:
bloodBankId: Unique identifier for each blood bank.
bloodBankName: Name of the blood bank.
location: Location of the blood bank.
contactNumber: Contact number of the blood bank.
email: Email address of the blood bank.
availableBloodGroups: Set of available blood groups (using a BloodType enum).

# Staff Entity:

Purpose: Represents information about staff members involved in the blood donation system.
Attributes:
staffId: Unique identifier for each staff member.
firstName and lastName: First and last names of the staff member.
position: Position or role of the staff member.
contactNumber: Contact number of the staff member.
email: Email address of the staff member.
Additional security-related fields can be added based on your requirements.

# Appointment Entity:

Purpose: Represents information about donor appointments.
Attributes:
appointmentId: Unique identifier for each appointment.
donor: Reference to the donor who scheduled the appointment.
appointmentDate: Date of the appointment.
appointmentTime: Time of the appointment.
location: Location of the appointment.
status: Status of the appointment.

# MedicalHistory Entity

Purpose: Represents the medical history of blood donors.
Attributes:
historyId: Unique identifier for each medical history entry.
donor: Reference to the donor whose medical history is recorded.
medicalCondition: Information about the donor's medical condition.
medications: Information about the medications the donor is taking.
allergies: Information about any allergies the donor has.

# Defining The Js

    - Example of creating an appointment
    const createAppointment = async () => {
      try {
        const response = await fetch('http://your-backend-url/api/v1/appointment/createAppointment', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            // appointment data
          }),
        });

        if (response.ok) {
          console.log('Appointment created successfully');
        } else {
          console.error('Failed to create appointment');
        }
      } catch (error) {
        console.error('An error occurred:', error);
      }
    };

    - Example of retrieving all appointments
    const getAllAppointments = async () => {
      try {
        const response = await fetch('http://your-backend-url/api/v1/appointment/showAppointment');
        const data = await response.json();

        if (response.ok) {
          console.log('Appointments:', data);
        } else {
          console.error('Failed to fetch appointments');
        }
      } catch (error) {
        console.error('An error occurred:', error);
      }
    };
