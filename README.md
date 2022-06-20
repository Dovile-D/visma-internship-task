Endpoints:

* /api/meetings - create new meetings
* /api/meeting/{meetingId} - delete a meeting
* /api/meetings/{meetingId} - add person to a meeting
* /api/meetings/{meetingId}/{personId} - remove person from a meeting
* /api/meetings - list all the meetings


Example of the meeting body:
____________________________
<img width="546" alt="Screenshot 2022-06-20 at 21 15 09" src="https://user-images.githubusercontent.com/75540351/174658654-6d118abe-ff12-4bcb-842d-8e517b798472.png">

_____________________________________________________

Example of person body:
_____________________________________________________
{
"id": 1,
"name": "Emilio",
"position": "Product Implementation Strategist"
}
______________________________________________________

JDK used: 17

Technologies used:
Spring Boot
Spring REST
Lombok
Maven
Jackson Jason
