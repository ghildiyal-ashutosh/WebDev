//Immediately Invoked Function Expression (IIFE)
(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $editBtn, $createBtn;
    var $firstNameFld, $lastNameFld,$roleFld;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();
    $(main);

    function main()
    {


       $createBtn =  $('.wbdv-create')
        $createBtn.click(checkUsername);



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

        userService.createUser(user);


    }
    function findAllUsers() { }
    function findUserById() { }
    function deleteUser() { }
    function selectUser() { }
    function updateUser() {  }
    function renderUser(user) { }
    function renderUsers(users) { }
})();
