
function User(username, password,  firstName, lastName, role, email, contact, dateOfBirth)
{
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName= lastName;
    this.contact= contact;
    this.email= email;
    this.role= role;
    this.dateOfBirth=  dateOfBirth;


    this.setUsername = setUsername;
    this.getUsername = getUsername;
    this.setPassword = setPassword;
    this.getPassword = getPassword;
    this.setFirstName = setFirstName;
    this.getFirstName = getFirstName;
    this.seLastName= setLastName;
    this.getLastName= getLastName;
    this.setContact= setContact;
    this.getContact= getContact;
    this.setEmail= setEmail;
    this.getEmail= getEmail;
    this.setRole= setRole;
    this.getRole= getRole;
    this.setDateOfBirth=  setDateOfBirth;
    this.getDateOfBirth=  getDateOfBirth;


    function setUsername(username) {
        this.username = username;
    }
    function getUsername() {
        return this.username;
    }

    function setPassword(password) {
        this.password = password;
    }
    function getPassword() {
        return this.password;
    }

    function setFirstName(firstName) {
        this.firstName = firstName;
    }
    function getFirstName() {
        return this.firstName;
    }

    function setLastName(lastName) {
        this.lastName = lastName;
    }
    function getLastName() {
        return this.lastName;
    }

    function setRole(role) {
        this.role = role;
    }
    function getRole() {
        return this.role;
    }

    function setContact(contact) {
        this.contact = contact;
    }
    function getContact() {
        return this.contact;
    }

    function setEmail(email) {
        this.email = email;
    }
    function getEmail() {
        return this.email;
    }

    function setDateOfBirth(dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    function getDateOfBirth() {
        return this.dateOfBirth;
    }

}
