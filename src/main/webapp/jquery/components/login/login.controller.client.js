(function () {

   $(main);
    var $usernameFld,$passwordFld;
    var userService;



    function main()
    {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        userService = new UserServiceClient();

        userService.signOut();

        $('#signInFld').click(checkUser);

    }

    function logIn(response)
    {

        if (response.firstName == "Negative")
        {
            alert(" LogIn Failed !!! Incorrect Username or Password...Try Again");
        }
        else
        {
          //  userId = response.id;
         //  window.location = "https://webdev-2.herokuapp.com/jquery/components/profile/profile.template.client.html"
            window.location = "http://localhost:8080/jquery/components/profile/profile.template.client.html"

        }

    }



    // checks whether the user credentials are authentic or not
    function checkUser()
    {

        var username = $usernameFld.val();
        var password = $passwordFld.val();

        var userCred = {
            username: username,
            password:password
        };
        userService.logIn(userCred).then(logIn);


    }

}) ();