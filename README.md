Links: 
Website Github
GitHub - Lethabo-ops814/CyberWalletWeb · GitHub

App Github
https://github.com/Ngcebenhle/CyberWallet 

Azure
https://dev.azure.com/ST103116400192/CyberWallet 

Video

https://www.dropbox.com/scl/fi/qhf8afupnslksmmfwa6ox/on-pc.mp4?rlkey=a5btwe59n4i8k87s3m8wv6m6c&st=y72mvto7&dl=0 




2.1 Introduction
Cyber Wallet is a digital financial management system. It is made up of two parts: a website that people open in a browser and a mobile application that people install on their phone. Both parts connect to the same backend and the same database, so a person who records a payment on the app will see it on the website, and the other way around.
The idea behind Cyber Wallet is simple. Most people earn money, spend money and want to know where it went. Many of them still keep this information in notebooks, bank messages, spreadsheets or in their heads. When it is time to check a balance, prepare a statement or speak to an accountant, the information is scattered and hard to find. Cyber Wallet puts all of it in one place.
The system is designed for two main groups of people: individuals who want to control their personal spending, and small business owners who need to keep track of income and expenses without hiring a full-time accountant. Both groups can use the system to record income and expenses, group them into categories, see charts of where their money goes, generate statements and pay for services. When they need expert help, they can book a consultation with a trained accountant.
The system also looks after the people who run it. Administrators can manage users, accountants, payments, bookings, messages and the services shown on the website. Accountants can see the bookings assigned to them and update their status. The system itself sends notifications so that users do not miss important events such as payments or bookings.
Because Cyber Wallet deals with money, security comes first. Passwords are stored as hashed values, card details are never stored, sessions expire after inactivity and all communication is protected with HTTPS.
This report explains the purpose of the website and the mobile application in plain terms. It covers the problem we want to solve, what each platform is for, how the website and the app differ, who uses the system, the main features and requirements, how the system is built, how data is kept safe and what we assumed along the way.
2.2 Problem Statement
Many individuals and small business owners struggle to manage their finances effectively because their income, expenses, statements and accounting support are spread across different platforms or handled manually. This leads to:
•	Poor financial tracking and missed payments
•	Weak budgeting habits and difficulty accessing financial records
•	Limited access to professional accounting support
Research into existing apps (iSaveMoney, Wallet and Money Tracker) showed that all of them track expenses, help with budgeting and use charts. It also showed three common weaknesses: some apps are too complex, many need manual input, and several limit their free features. Cyber Wallet was designed to close these gaps.
2.3 Purpose of the System
The purpose of Cyber Wallet is to bring all financial management needs into one secure place. It lets users record and categorise income and expenses, see their financial position on a dashboard, generate statements, make payments and book consultations with trained accountants. It also gives administrators the tools to manage users, accountants, payments, bookings and messages.
Purpose of the website
The website is the full-featured platform. It is accessed through a desktop or mobile browser and serves three groups of visitors:
•	Public visitors: a landing page (“Manage Your Money Smarter”), services page, contact form, login, registration and an application form that explains the service and builds trust before personal details are requested.
•	Registered users: a private area with a dashboard, statistics, statements, payments, consultation booking and profile management.
•	Administrators: a separate admin area for managing users, accountants, payments, bookings, messages and the services shown on the site.
Purpose of the mobile application
The mobile application gives users quick, everyday access to their finances from a smartphone. It focuses on the actions people perform most often: logging in, adding income and expenses, checking the dashboard, planning upcoming bills, viewing statements, making payments and receiving push notifications. A fixed bottom navigation bar (Home, Transactions, Add, Planner, Profile) keeps these actions within easy reach, and swipe navigation moves between the dashboard views.
How the two platforms work together
Both platforms use the same backend API and database. A transaction recorded on the mobile app appears on the website dashboard, and the reverse. The website offers the complete feature set and administration, while the mobile app offers fast, convenient access for day-to-day use.
2.4 Difference between the Website and the Mobile App
The website and the mobile app share the same data and the same purpose, which is to help people manage their money. They are not copies of each other, though. Each one is designed for a different way of working.
The website is the complete service. It is opened in a browser, works on desktop, tablet and phone screens, and has public pages for visitors as well as the private user area and the admin area. It is best when someone sits down to do bigger tasks: reading tables, generating statements, booking consultations or managing the system.
The mobile app is the everyday companion. It is installed on a smartphone and is built for short, quick actions: adding an expense, checking the dashboard, looking at upcoming bills and receiving alerts. It is best when someone is on the move and wants an answer in seconds.
Aspect	Website	Mobile app
Main purpose	Full financial service, information about Cyber Wallet and system administration.	Quick, everyday money management on the go.
How it is accessed	Through a web browser on a desktop, tablet or phone. Nothing needs to be installed.	Installed on a smartphone (Android) and opened from the home screen.
Who uses it	Public visitors, registered users and administrators.	Registered users. The app starts with a splash screen, welcome slides and login or registration.
Public pages	Landing page, services, contact form, application form, login and registration.	No public information pages. The app focuses on the user’s own finances.
Admin functions	A separate admin area to manage users, accountants, payments, bookings, messages and services.	Not part of the app. Administration is done on the website.
Navigation	A top menu with links between pages, grouped into public, user and admin areas (14 pages).	A layered structure: login, a dashboard that leads to the main sections, and a fixed bottom bar (Home, Transactions, Add, Planner, Profile). Users can swipe between dashboard views.
Screen layout	Wide layouts with tables, charts and forms side by side.	Compact cards in a single column, with large touch-friendly buttons.
Alerts	Notifications are listed in the user’s account.	Push-style notifications alert users to payments, bookings and account activity.
Typical tasks	Generate and download statements, review payment and booking tables, send a contact message, apply for a service, manage the system.	Add income or expenses, check the balance and charts, view the planner and upcoming bills, make a payment.
Data	Uses the shared backend and database.	Uses the same backend and database, so both always show the same information.

2.5 Target Users and Actors
Actor	Primary responsibilities
User	Register, log in, manage profile, track finances, generate statements, make payments and book consultations.
Admin	Manage users, accountants, payments, bookings, messages and system services.
Accountant	View assigned consultation bookings and update their status.
System (Notification Service)	Automatically send alerts and notifications to users.

2.6 Key Features
Feature	Platform	What it does
Registration and login	Web and mobile	Secure account creation and authentication with hashed passwords.
Dashboard	Web and mobile	Overview of income, expenses and balance with graphs, spending progress and upcoming bills.
Income and expense tracking	Web and mobile	Record and categorise transactions, including custom categories, recurring items and optional receipt images.
Statistics	Web and mobile	Charts and summaries of financial patterns, such as expenses by category and income versus expenses.
Statements	Web and mobile	Generate financial statements for a chosen date range and download them as PDF.
Payments	Web and mobile	Secure payment processing through a third-party gateway and a payment history. No card data is stored.
Consultation booking	Web and mobile	Book, view and cancel sessions with accountants.
Planner and reminders	Web and mobile	Monthly planner, upcoming bills and reminders for recurring transactions.
Notifications	Web and mobile	Alerts for payments, bookings and account activity, sent as push notifications on the app.
Goals and spending limits	Mobile	Goal setting, spending limits and automated payments, with points and rewards (gamification).
Contact form	Web	Send enquiries to the support team; admins respond.
Admin control centre	Web	Manage users, accountants, services, payments, bookings and messages.

2.7 System Requirements
Functional requirements
ID	Requirement	Description
FR1	User Registration	Allow new users to create an account
FR2	User Login	Secure authentication with hashed passwords
FR3	Profile Management	Users can update personal details
FR4	Dashboard	Overview of income, expenses and balance
FR5–FR6	Financial Tracking	Record and categorise income and expenses
FR7	Statistics Page	Visual financial patterns and summaries
FR8–FR9	Statement Generation	Generate and download financial statements as PDF
FR10–FR11	Payment System	Process and view payment history securely
FR12–FR14	Consultation Booking	Book, view and cancel accountant sessions
FR15	Contact Form	Send enquiries to the support team
FR16	Notifications	Alerts for payments, bookings and account activity
FR17–FR22	Admin Functions	Manage users, accountants, payments, bookings and messages
FR23–FR24	Accountant Functions	View assigned bookings and update consultation status

Non-functional requirements
Category	Requirement
Usability	Intuitive interface for all user types
Responsiveness	Works on all screen sizes (mobile, tablet, desktop)
Security	Hashed passwords, HTTPS, session timeouts, account lockout
Performance	Fast load times and reliable uptime
Scalability	Architecture can grow with increasing users
Privacy	Collect only necessary personal and financial data
Payment safety	Secure third-party payment gateway; no card data stored
Backup and recovery	Regular database backups to prevent data loss

2.8 System Architecture
Cyber Wallet uses a three-layer architecture:
•	View layer: the website and mobile app screens, forms, dashboards and buttons that users interact with.
•	Domain layer: the backend API containing business rules, validation, authentication, statement generation, payment and booking logic, and connections to external services such as the payment gateway.
•	Data layer: the database that stores users, transactions, statements, payments, bookings, accountants, messages, notifications, services and application forms, supporting create, read, update and delete operations.
When a user acts (for example logging in or generating a statement), the request travels from the view layer to the backend API, which validates it, applies the business rules, reads or writes the database and returns the response to the website or mobile app.
The system is divided into 15 subsystems: user account, authentication and authorisation, dashboard, income and expense tracking, statistics, statement generation, payment, consultation booking, accountant management, contact/message, notification, admin control centre, database, website frontend and mobile application frontend.
2.9 Data Model and Traceability
The main classes are User, Admin, Accountant, Transaction (Income and Expense), Statement, Payment, ConsultationBooking, Message, Notification, Dashboard, Service and ApplicationForm. Key business rules are one-to-many relationships: one user has many transactions, statements, payments and bookings; one accountant manages many bookings; one admin manages many accountants; and many transactions feed into one statement.
Requirement IDs	Focus area	Related entities
FR1–FR3	User account management	User, Authentication System
FR4–FR7	Financial tracking and dashboard	Transaction, Dashboard
FR8–FR9	Statement generation	Statement
FR10–FR11	Payment processing	Payment
FR12–FR14	Consultation booking	ConsultationBooking, Accountant
FR15–FR16	Communication and notifications	Message, Notification
FR17–FR22	Admin management	Admin, User, Accountant, Payment, Message
FR23–FR24	Accountant functions	Accountant, ConsultationBooking

2.10 Security
Because Cyber Wallet handles sensitive financial data, security is a top priority.
Security area	Requirement
Password security	All passwords are stored as hashed values, never plain text.
Authentication	Only registered users can access private financial data.
Authorisation	Admin functions are restricted to admin roles.
Payment safety	No card details are stored; a secure third-party payment gateway is used.
Data privacy	Only necessary user and financial data is collected.
Session security	Sessions expire automatically after inactivity.
Account protection	Temporary lockout after too many failed login attempts.
Secure connection	All communication uses HTTPS.
Backups	Regular database backups ensure data recovery.

2.11 Assumptions and Constraints
Assumptions
•	Users have internet access when using the website or mobile app.
•	Users enter accurate financial data when recording income and expenses.
•	Payments are processed through a secure third-party payment provider.
•	Accountants are registered and managed only by administrators.
•	Financial statements reflect only the data entered by users.
Constraints
•	The project must be delivered within the planned schedule and budget.
•	Payment processing must follow industry security best practices.
•	Personal and financial data must be protected at all times.
•	The website must be responsive across all screen sizes.
•	The database must prevent duplicated or inconsistent financial records.
2.12 Conclusion
Cyber Wallet gives users one secure place to track money, plan ahead, generate statements, pay and consult accountants. The website provides the complete service and administration, and the mobile app provides fast everyday access. Together they address the fragmented, manual and complicated money management described in the problem statement.
 
