# cms
- clone repo 
- checkout branch dev `git checkout dev` (if dev in not present do first `git pull dev`)
- create you branch `git branch _branchName_` then `git checkout_branchName_`
- add "application.properties" in `src/main/resources` and the required code
- do your changes  


## API Request Body structure :

### Event :
- {
  - "eventId": 0,
  - "eventDate": "2022-10-19",
  - "eventName": "eventname",
  - "eventStartTime": "15:00:00",
  - "eventEndTime": "18:00:00",
  - "eventAge": 18,
  - "eventLogoUrl": "someurl.com"
- }

### User :
- {
  - "userId" : 0,
  - "userFirstName" : "new",
  - "userLastName" : "user",
  - "userEmail" : "new@user.com",
  - "userPassword" : "password2",
  - "userContactNo" : "9876567",
  - "userGender" : "Male",
  - "userDOB" : "2022-10-19"
-   }