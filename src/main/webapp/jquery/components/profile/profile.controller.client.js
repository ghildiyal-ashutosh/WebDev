(function  () {

    var user1;    //stores the information of the current user
    var userService = new UserServiceClient();
    var $usernameFld, $contactFld ;
    var $emailFld , $roleFld ;
    var $dobFld ;

    $(main);

    function main ()
    {
        userService = new UserServiceClient();
        $usernameFld = $('#usernameFld');
        $contactFld = $('#contactFld');
        $emailFld = $('#emailFld');
        $roleFld = $('#roleFld');
        $dobFld = $('#dobFld');

         userService.checkCurrentUser().then(renderUser);


        $('.update').click(updateProfile);

        $('#signOut').click (signOut);

    }

    function  signOut()
    {
       // location.href = "https://webdev-2.herokuapp.com/jquery/components/login/login.template.client.html";
        userService.signOut()
                    .then (redirect);
        //(window.location.href =  "http://localhost:8080/jquery/components/login/login.template.client.html");


    }
    function updateProfile()
    {
        var userId = user1.id;

        $contactFld = $('#contactFld').val();
        $emailFld = $('#emailFld').val();
        $roleFld = $('#roleFld').val();
        $dobFld = $('#dobFld').val();

        var user = new User("", "", "","",$roleFld,$emailFld,$contactFld,$dobFld);


        userService.updateProfile(userId,user)
                    .then(success);
    }


// HELPER FUNCTIONS

    function success(response)
    {
        if (response.firstName == "Negative")
            alert ("Problem Updating");
        else
            alert ("Successfully Updated");
    }




    //loads the input fields with the information present in the database

    function renderUser(user)
    {
        $('#usernameFld').val(user.username);
        $('#contactFld').val(user.contact);
        $('#roleFld').val(user.role);
        $('#dobFld').val(user.dateOfBirth);
        $('#emailFld').val(user.email);
        user1 = user;

    }


    function redirect(response)
    {
        if (response)
        {
           // var url = "http://localhost:8080/jquery/components/login/login.template.client.html";
            var url ="https://webdev-2.herokuapp.com/jquery/components/login/login.template.client.html"
            window.location.href = url
        }

    }


 /**   function getIdByUrl()
    {

        var url = document.URL;
        var id = url.substring((url.indexOf("userid="))+7)
        return id;
    }
    */



}) ();