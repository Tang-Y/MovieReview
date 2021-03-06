# MovieReview
Users use this app for search/add movies and add/review their comments

Part 1 – Data and Data Manipulation
1) Determine the POJO that will be required
2) Make POJOs entities, and create appropriate JPA Repositories to work with stored data as required.
3) Use a data.sql file to hard-code at least three reviews

Part 2 – Controllers and Views
1) Controllers which can dispatch to forms and HTML tables where the movies and reviews are displayed
2) It is able to sort movies by clicking on a table heading (headings as hyperlinks) that call appropriate OrderBy queries from the relevant repositories
3) It should obviously also be able to enter new movies and/or reviews
4) It should be obvious how to navigate the system and how to add additional info to the already partially populated database
5) When adding movies or reviews, it must use Model Attribute form binding. If performing a search function, which is default to Request Params or a Path Variables
6) A Thymeleaf pages which display dynamic Review content. Display all Review content for every movie in a nicely formatted and styled fashion
7) The project uses an H2 database for storage
