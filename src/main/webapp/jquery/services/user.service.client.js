function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.findUserByUsername = findUserByUsername;
    this.register = register;
    this.logIn = logIn;
    this.updateProfile = updateProfile;
    this.resetPassword = resetPassword;
    this.checkCurrentUser = checkCurrentUser;
    this.signOut = signOut;
  // this.url = 'http://localhost:8080/api/user';
    this.url = 'https://webdev-2.herokuapp.com/api/user'
    var self = this;


    function createUser(user)
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
           return response.json();
        });
    }


     function findAllUsers(callback)
     {
         return fetch(self.url)
         .then(function (response) {
         return response.json();
     });

     }


     function findUserById(userId)
     {
         return fetch (self.url+'/'+userId )
             .then(function (response)
             {
                 return response.json();
             });
     }

     //same function will be used for updating profile (UpdateProfile)
   function updateUser(userId, user)
   {
       return fetch (self.url+'/'+userId,
           {
               method : 'put',
               body : JSON.stringify(user),
               headers:
                   { 'content-type' : 'application/json'}

           }).then (function (response)
       {
           return response.json();
       });
   }

     function deleteUser(userId, callback)
     {
         return fetch (self.url + '/' + userId,
             {
                 method : 'delete'

             });
     }

     function findUserByUsername(username)
     {
         return fetch(self.url + '/' + 'username' + '/'+ username)
             .then (function (response)
             {
                 return response.json();
             });
     }

    function register(user) {

        return fetch(self.url + '/'+ "register",{
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            },
            'credentials' : 'include'
        }).then(function (response) {
            return response.json()
        });

    }


    function logIn(userCred) {
        return fetch(self.url+'/'+'logIn', {
            method: 'post',
            body: JSON.stringify(userCred),
            credentials : 'include',
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response){
            return response.json();
        });

    }


    function updateProfile(userId, user)
    {
        return fetch (self.url+'/'+'updateProfile'+ '/'+ userId,
            {
                method : 'put',
                body : JSON.stringify(user),
                credentials : 'include',
                headers:
                    { 'content-type' : 'application/json'}

            }).then (function (response)
        {
            return response.json();
        });
    }

    function resetPassword(credentials)
    {
        return fetch (self.url + '/' + 'reset', {
            method : 'put',
            body : JSON.stringify(credentials),
            headers:
                {
                    'content-type' : 'application/json'
                }
        }).then(function (response)
        {
            return response.json();


        });

    }

    function checkCurrentUser()
    {
        return fetch(self.url + '/' + 'checkCurrentUser',
            {
                credentials : 'include'
            }).then (function (response)
        {
            return response.json();
        });

    }

    function signOut()
    {
        return fetch (self.url + '/' + 'logOut',
            {
                method :'post'
            });
    }
}
