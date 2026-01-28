📊 AI-Powered Personal Finance Tracker
📌 Project Overview

The AI-Powered Personal Finance Tracker is a full-stack web application designed to help users manage their personal finances efficiently.
It allows users to track income and expenses, analyze spending patterns, plan budgets, and receive AI-powered financial insights and recommendations using ChatGPT.

This project follows industry-standard layered architecture with secure JWT authentication and AI integration using Spring AI.

🎯 Main Goal of the Project

The main goal of this project is to help users:

Track income and expenses

Analyze spending behavior

Plan monthly budgets

Get AI-based financial advice and budget recommendations

Improve saving habits through intelligent insights

Instead of just showing raw financial data, the system converts data into actionable financial intelligence.

🧠 Why This Project is Important

Many people track expenses manually but fail to understand spending patterns.
This project bridges that gap by:

Providing automated financial analysis

Generating AI-driven savings recommendations

Helping users build financial discipline

🏗️ System Architecture
React Frontend
      |
 REST API (JSON)
      |
Spring Boot Backend
      |
Spring AI (ChatGPT API)
      |
MySQL Database

🛠️ Technologies Used
🔹 Backend

Java 17

Spring Boot 3

Spring Security (JWT Authentication)

Spring Data JPA & Hibernate

Spring AI (ChatGPT Integration)

Maven

🔹 Database

MySQL

🔹 Frontend

React.js

Axios

Chart.js / Recharts

Tailwind CSS / Material UI

🔹 Tools & Utilities

Postman

Git & GitHub

IntelliJ / Eclipse / STS

OpenAI API

🚀 Features
✅ Authentication & Security

User Registration & Login

JWT Token-based Authentication

Role-Based Authorization (ADMIN / USER)

Password Encryption (BCrypt)

💰 Income & Expense Management

Add, update, delete income

Add, update, delete expenses

Category-wise expense tracking

User-specific financial data isolation

📊 Reports & Analytics

Monthly & yearly income/expense reports

Savings calculation

Category-wise spending analysis

Graphical dashboards in React

🤖 AI-Powered Financial Insights

Using ChatGPT, the system provides:

Personalized savings tips

AI-generated monthly budget plan

Spending behavior analysis

Financial habit improvement suggestions

🗄️ Database Design
📌 Tables
👤 Users
users(user_id, name, email, password, role)

💰 Income
income(income_id, user_id, amount, source, income_date)

💸 Expense
expense(expense_id, user_id, category, amount, description, expense_date)

📊 Budget
budget(budget_id, user_id, category, monthly_limit)

🤖 AI Insights
ai_insights(insight_id, user_id, insight_text, generated_on)

🔄 Project Flow
🔐 Authentication Flow

User logs in

JWT token generated

Token sent in Authorization header

JwtFilter validates token

Role-based access granted

💰 Expense Flow
Client → Controller → Service → Repository → MySQL

🤖 AI Advice Flow
Client → AI Controller → AI Service → ChatGPT API → Response

com.example.finance
│
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── ai
├── exception
├── config
└── FinanceApplication.java


🔑 API Endpoints
🔐 Auth APIs
POST   /api/auth/register
POST   /api/auth/login

👤 User APIs (Admin Only)
GET    /api/users
GET    /api/users/{id}
PUT    /api/users/{id}
DELETE /api/users/{id}

💰 Income APIs
POST   /api/incomes/{userId}
GET    /api/incomes/user/{userId}
PUT    /api/incomes/{id}
DELETE /api/incomes/{id}

💸 Expense APIs
POST   /api/expenses/{userId}
GET    /api/expenses/user/{userId}
PUT    /api/expenses/{id}
DELETE /api/expenses/{id}

🤖 AI APIs
GET /api/ai/chatgpt-advice/{userId}
POST /api/ai/budget/generate

⚙️ How to Run the Project
✅ Backend

Clone repository

Create MySQL DB

CREATE DATABASE finance_db;


Add OpenAI API key in application.properties

Run Spring Boot Application

✅ Frontend
npm install
npm start

🧪 Sample Login Request
POST /api/auth/login
{
  "email": "user@gmail.com",
  "password": "12345"
}

💡 AI Prompt Example
Based on my expenses, suggest ways to reduce spending and generate a monthly budget plan.

🧠 Interview Value of This Project

✔ Real-world financial problem solving
✔ Full-stack integration (React + Spring Boot)
✔ AI recommendation system
✔ Secure JWT authentication
✔ Database design & analytics
✔ Clean layered architecture

🗣️ One-Line Interview Summary

“This project tracks personal finances and uses AI to analyze spending behavior and generate personalized savings and budget recommendations.”

📌 Future Enhancements

Expense prediction using ML

Bank API integration

Mobile App version

Notifications & alerts

Export reports as PDF

👨‍💻 Author

Devendra Solanki
Java Backend / Full Stack Developer