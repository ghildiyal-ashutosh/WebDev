(function () {

   $(main);
    var $usernameFld,$passwordFld;
    var userService;



    function main()
    {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        userService = new UserServiceClient();

        $('#signInFld').click(checkUser);

    }

    function checkUser() {

        var username = $usernameFld.val();
        var password = $passwordFld.val();

        var userCred = {
            username: username,
            password:password
        };
        userService.logIn(userCred).then(logIn);


    }

    function logIn(response) {

        if (response.firstName == "Negative")
        {
            alert("Incorrect Username or Password...Try Again");
        }
        else
        {
            userId = response.id;
            window.location = "http://localhost:8080/jquery/component/profile/profile.template.client.html?userid=" +userId;
        }

    }

}) ();