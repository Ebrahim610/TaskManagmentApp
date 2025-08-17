<img width="666" height="939" alt="Image" src="https://github.com/user-attachments/assets/e9a888f3-f567-4779-b488-500297f98a5a" />

<img width="2002" height="1062" alt="Image" src="https://github.com/user-attachments/assets/88ae644b-a86f-4c9f-95e9-d2cb0bd85424" />

# Performance Report

 compared a moderately complex query in Room using `@Query` and `@RawQuery`.

### Query Tested
**"Get all Projects with more than 3 tasks"**

```sql
SELECT p.* FROM projects p
JOIN tasks t ON p.id = t.projectId
GROUP BY p.id
HAVING COUNT(t.id) > 3;
