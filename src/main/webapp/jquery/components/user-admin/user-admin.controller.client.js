//Immediately Invoked Function Expression (IIFE)
(function () {
    var $usernameFld, $passwordFld;
    var $searchBtn, $updateBtn, $createBtn;
    var $firstNameFld, $lastNameFld,$roleFld;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();
    var uid;  // Global variable,stores user id ,used while updating a user
    $(main);

    function main()
    {

        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-user');
        $createBtn =  $('.wbdv-create');
        $updateBtn = $('.wbdv-update');

        $searchBtn =$ ('.wbdv-search');
        $createBtn.click(checkUsername);
        $updateBtn.click(check);
        $searchBtn.click(searchByUsername);


      findAllUsers();
    }

    function createUser(user)
    {

        if (user.firstName == "Negative")
        {

            $usernameFld = $('#usernameFld').val();
            $passwordFld = $('#passwordFld').val();
            $firstNameFld = $('#firstNameFld').val();
            $lastNameFld = $('#lastNameFld').val();
            $roleFld = $('#roleFld').val();

            var user = new User($usernameFld, $passwordFld, $firstNameFld, $lastNameFld, $roleFld, null, null, null);


            userService.createUser(user)
                .then(renderUser)
                .then (resetForm);

        }
        else
        {
            alert ("Username exist in database...Try some other username");
        }

    }
    function findAllUsers()
    {
        userService.findAllUsers().then(renderUsers).then(resetForm);
    }


    function findUserById(id)
    {
        var user = userService.findUserById(id);
        return user;

    }
    function deleteUser(event)
    {

        userService.deleteUser(findUserId(event))
                   .then(findAllUsers);
    }

    function selectUser(event)
    {
        var userId = findUserId(event);
        findUserById(userId).then(loadUser);

    }

    function updateUser(user)
    {

        if (user.id == uid || user.firstName == "Negative")
             $(updateDatabase);
        else
            alert("Username exist in database...Try a different name");
    }
    function renderUser(user)
    {
        var clone = $userRowTemplate.clone();
        clone.attr('id', user.id);
        clone.find('.wbdv-remove').click(deleteUser);
        clone.find('.wbdv-edit').click(selectUser);
        clone.find('.wbdv-username').html(user.username);
        clone.find('.wbdv-first-name').html(user.firstName);
        clone.find('.wbdv-last-name').html(user.lastName);
        clone.find('.wbdv-role').html(user.role);
        $tbody.append(clone);

    }

    function renderUsers(users)
    {
        $tbody.empty();

        for (var i=0;i<users.length;i++)
        {
            var user = users[i];
            var clone = $userRowTemplate.clone();

            clone.attr('id', user.id);
            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(selectUser);

            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            $tbody.append(clone);
            }

    }


//HELPER FUNCTIONS


//finds a user's id

    function findUserId(event)
    {
        var position = $(event.currentTarget);

        var userId = position
            .parent()
            .parent()
            .parent()
            .attr('id');



        return userId;
    }

// loads a user's detail on the top screen once the edit button is clicked

    function loadUser(user)
    {
        if (user.firstName == "Negative")
            alert("No such user exist");
        else {

            uid = user.id;
            $('#usernameFld').val(user.username);
            $('#firstNameFld').val(user.firstName);
            $('#lastNameFld').val(user.lastName);
            $('#roleFld').val(user.role);
            $('#passwordFld').val(user.password);
        }

    }
// check whether the name of the new user is in the database

    function checkUsername()
    {

        var $usernameFld = $('#usernameFld').val();

        userService.findUserByUsername($usernameFld).then(createUser);

    }

// check whether the updated name is in the database
    function check()
    {
        var $usernameFld = $('#usernameFld').val();

        userService.findUserByUsername($usernameFld).then(updateUser);

    }

    // updates the database once update button is clicked


    function updateDatabase()
    {
        $usernameFld = $('#usernameFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $passwordFld = $('#passwordFld').val();
        $roleFld = $('#roleFld').val();



        var newUser = new User($usernameFld,$passwordFld,$firstNameFld,$lastNameFld,$roleFld);


        userService.updateUser(uid,newUser).then(updateStatus);
    }

    // notifies about the result of the updation

    function updateStatus(user)
    {
        if (user.firstName == "Negative")
            alert("No such user exist");
        else
            alert("User updated successfully");

        uid = 0;

        findAllUsers();
    }

    //search option implemented

    function searchByUsername(event)
    {
        var uname = $('#usernameFld').val();

        userService.findUserByUsername(uname).then(loadUser);

    }

 // reset the input fields

    function resetForm()
    {

        $('#usernameFld').val('');
        $('#firstNameFld').val('');
        $('#lastNameFld').val('');
        $('#passwordFld').val('');

    }

})();
