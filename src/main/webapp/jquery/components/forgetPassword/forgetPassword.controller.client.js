(function () {

    var $contactFld, $emailFld;
    var userService, $signInFld, $submitFld;
    var $passwordFld, $cpasswordFld;
    main();
    function main()
    {
        $contactFld = $('#contactFld');
        $emailFld = $('#emailFld');
        $passwordFld = $('#passwordFld');
        $cpasswordFld = $('#cpasswordFld');

        userService = new UserServiceClient();

        $('#signInFld').click(signIn);

        $('#submitFld').click(resetPassword);
    }


    function signIn()
    {
        location.href = "https://webdev-2.herokuapp.com/jquery/components/login/login.template.client.html";
    }

    function resetPassword()
    {
        if ($passwordFld.val() == $cpasswordFld.val()) {
            var newCred = {
                contact: $contactFld.val(),
                email: $emailFld.val(),
                password: $passwordFld.val()
            };
            userService.resetPassword(newCred).then(success);
        }

        else
        {
            alert ("password didn't match try again");
        }
    }

    function success(response) {
        if (response.firstName == "Negative") {
            var contact = $contactFld.val();
            var email = $emailFld.val();
            alert("No record was found corresponding to these details:" + "Contact:" + "   "+  contact + "  "+
                "Email:" + "   " + email );
        }
        else {
            alert(" Password Successfully changed");
        }
    }

}) ();
