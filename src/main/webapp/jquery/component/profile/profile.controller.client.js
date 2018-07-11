(function  () {

    var user1;

    $(main);

    var userService = new UserServiceClient();
    var $usernameFld, $contactFld ;
    var $emailFld , $roleFld ;
    var $dobFld ;

    function main ()
    {
        userService = new UserServiceClient();
        $usernameFld = $('#usernameFld');
        $contactFld = $('#contactFld');
        $emailFld = $('#emailFld');
        $roleFld = $('#roleFld');
        $dobFld = $('#dobFld');


        loadUser();

        $('.update').click(updateUser);

        $('#signOut').click (signOut);

    }

    function  signOut() {

        location.href = "http://localhost:8080/jquery/component/login/login.template.client.html";

    }



    function getIdByUrl()
    {

        var url = document.URL;
        var id = url.substring((url.indexOf("userid="))+7)
        return id;
    }



    function updateUser()
    {
        var userId = user1.id;
        var password = user1.password;
        var firstName = user1.firstName;
        var lastName = user1.lastName;



        $usernameFld = $('#usernameFld').val();
        $contactFld = $('#contactFld').val();
        $emailFld = $('#emailFld').val();
        $roleFld = $('#roleFld').val();
        $dobFld = $('#dobFld').val();

        var user = new User($usernameFld, password,
            firstName,lastName,$roleFld,$emailFld,$contactFld,$dobFld);


        userService.updateUser(userId,user)
            .then(success);

    }

    function success(response){
        if (response.firstName == "Negative")
            alert ("Problem Updating");
        else
            alert ("Successfully Updated");
    }

    function loadUser()
    {
        var userId = getIdByUrl();
        userService.findUserById(userId).then (renderUser);
    }

    function renderUser(user)
    {
        $('#usernameFld').val(user.username);
        $('#contactFld').val(user.contact);
        $('#roleFld').val(user.role);
        $('#dobFld').val(user.dateOfBirth);
        $('#emailFld').val(user.email);
        user1 = user;

    }


}) ();