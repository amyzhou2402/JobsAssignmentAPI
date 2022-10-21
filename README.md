# JobsAssignmentAPI
Springboot project. 1-M relationship between assigning temps 1+ jobs

**Endpoints** 

`GET /jobs` - Fetch all jobs

`GET /temps` - List all temps

`GET /jobs/{id}` - (id, name, tempId, startDate, endDate)

`GET /temps/{id}` - get temp by id (should also display jobs they’ve been assigned to)

`GET /jobs?assigned={true|false}` - Filter by whether a job is assigned to a temp or not

`GET /temps?jobId={jobId}` - List temps that are available for a job based on the jobs date range

`POST /jobs` - Creates a job

`POST /temps` - Create a temp

`PATCH /jobs/{id}` - Updates job, endpoint should be used to assign temps to jobs
