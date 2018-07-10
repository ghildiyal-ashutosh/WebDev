function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
   // this.findUserById = findUserById;
   // this.deleteUser = deleteUser;
   // this.updateUser = updateUser;
    this.url = 'http://localhost:8080/api/user';
    var self = this;


    function createUser(user, callback)
    {

        return fetch (self.url,
        {
            method : 'post',
            body :  JSON.stringify(user),
            headers :
                {
                    'content-type' : 'application/json'
                }
        }).then(function (response){
           return (response.json);
        });
    }


     function findAllUsers(callback)
     {
         return fetch(self.url)
         .then(function (response) {
         return response.json();
     });

     }


   // function findUserById(userId, callback) { }
   // function updateUser(userId, user, callback) { }
   // function deleteUser(userId, callback) { }
}
