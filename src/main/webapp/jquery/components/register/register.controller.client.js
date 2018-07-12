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
                window.alert(" Registration Failed !!! Password Do not Match... Try Again");
            }


        }

    function signIn()
    {
        location.href = "https://webdev-2.herokuapp.com/jquery/components/login/login.template.client.html";

    }

// redirects to Edit Profile Page
    function success(response)
    {
        if (response.firstName == "Negative")
            window.alert("Registration Failed !!! Username exists in the database");
        else {
            window.alert("you have been added successfully");


            var id1 = response.id;

            //  window.location = '/player_detail?username=' + name;
            window.location = "https://webdev-2.herokuapp.com/jquery/components/profile/profile.template.client.html?userid="
                               +id1 ;
        }
    }


})();