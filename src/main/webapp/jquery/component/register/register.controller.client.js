(function () {
        var $usernameFld, $passwordFld, $verifyPasswordFld;
        var $registerBtn;
        var userService;
        $(main);

        function main()
        {

            userService = new UserServiceClient();
            $('#register').click(register);
            $('#signIn').click(signIn);


        }

        function signIn()
        {
            location.href = "http://localhost:8080/Jquery/components/login/login.template.client.html";

        }
        function register()
        {

            $passwordFld = $('#passwordFld').val();
            $verifyPasswordFld = $('#verifyPasswordFld').val();
            $usernameFld = $('#usernameFld').val();

            var user = new User($usernameFld,$passwordFld);

            if ($passwordFld == $verifyPasswordFld) {

                userService.register(user).then(success);

            }
            else
            {
                window.alert("Password Doesnot Match... Try Again");
            }


        }


    function success(response)
    {
        if (response.firstName == "Negative")
            window.alert("Cant register, username exist in the database");
        else {
            window.alert("you have been added successfully");


            var id1 = response.id;

            //  window.location = '/player_detail?username=' + name;
            window.location = "http://localhost:8080/Jquery/components/profile/profile.template.client.html?userid="
                               +id1 ;
        }
    }


})();