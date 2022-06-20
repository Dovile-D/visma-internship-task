Endpoints:

* /api/meetings - create new meetings
* /api/meeting/{meetingId} - delete a meeting
* /api/meetings/{meetingId} - add person to a meeting
* /api/meetings/{meetingId}/{personId} - remove person from a meeting
* /api/meetings - list all the meetings


Example of the meeting body:
____________________________
{
"id": "1",
"name": "Spring Framework",
"responsiblePerson": {
"id": 7,
"name": "Edgar",
"position": "International Data Specialist"
},
"description": "It not only provides dependency injection, which makes writing testable code easier but even many libraries and utility classes like JdbcTemplate, which makes day-to-day Java programming better.",
"category": "Short",
"type": "InPerson",
"startDate": "2022-07-13 14:47",
"endDate": "2022-07-13 16:03",
"participants": [
{
"id": 11,
"name": "Angela",
"position": "Central Operations Producer"
},
{
"id": 12,
"name": "Trevor",
"position": "District Paradigm Officer"
},
{
"id": 13,
"name": "Edwin",
"position": "Human Applications Strategist"
},
{
"id": 14,
"name": "Gwendolyn",
"position": "Internal Program Manager"
},
{
"id": 15,
"name": "Dave",
"position": "Legacy Assurance Architect"
},
{
"id": 16,
"name": "Cora",
"position": "International Implementation Planner"
}
]
}
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