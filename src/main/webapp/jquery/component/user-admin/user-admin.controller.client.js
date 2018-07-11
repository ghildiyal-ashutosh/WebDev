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
        $updateBtn.click(updateUser);


      findAllUsers();
    }

    function checkUsername()
    {

        createUser();

    }


    function createUser()
    {

        $usernameFld = $('#usernameFld').val();
        $passwordFld =$('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();

        var user = new User($usernameFld,$passwordFld,$firstNameFld,$lastNameFld,$roleFld,null,null,null);

        userService.createUser(user)
                    .then(renderUser);
        alert("User Created");
        }
    function findAllUsers()
    {
        userService.findAllUsers().then(renderUsers);
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
    function selectUser() { }
    function updateUser()
    {
        userService.findUserById(uid).then(updateDatabase);
    }

    function updateDatabase(user)
    {
        $usernameFld = $('#usernameFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $passwordFld = $('#passwordFld').val();
        $roleFld = $('#roleFld').val();

        var email = user.email;                  // data from database, that was not changed by the admin
        var dateOfBirth = user.dateOfBirth;
        var contact = user.contact;

        var newUser = new User($usernameFld,$passwordFld,$firstNameFld,$lastNameFld,$roleFld,email,contact,dateOfBirth);

        userService.updateUser(uid,newUser).then(updateStatus);
    }

    function updateStatus(user)
    {
        if (user.firstName == "Negative")
            alert("No such user exist");
        else
            alert("User updated successfully");

        findAllUsers();
    }

    function renderUser(user)
    {
        var clone = $userRowTemplate.clone();
        clone.attr('id', user.id);
        clone.find('.wbdv-remove').click(deleteUser);
        clone.find('.wbdv-edit').click(editUser);
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
            clone.find('.wbdv-edit').click(editUser);

            clone.find('.wbdv-username').html(user.username);
            clone.find('.wbdv-first-name').html(user.firstName);
            clone.find('.wbdv-last-name').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            $tbody.append(clone);
            }

            console.log($tbody);
            }
    function editUser(event)
    {
        var userId = findUserId(event);

        console.log(userId);

        findUserById(userId).then(loadUser);

    }

    function loadUser(user)
    {
        console.log(user);
        uid = user.id;
        $('#usernameFld').val(user.username);
        $('#firstNameFld').val(user.firstName);
        $('#lastNameFld').val(user.lastName);
        $('#roleFld').val(user.role);
        $('#passwordFld').val(user.password);
    }

})();
