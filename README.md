# Railora 🚆

Railora is a modern railway ticket booking Android application designed to provide a smooth and intuitive travel experience. The project focuses on clean architecture, beginner-friendly implementation, and a polished UI inspired by modern travel applications.

---

## Tech Stack

### Android
- Kotlin
- XML
- MVVM Architecture
- Navigation Component
- ViewBinding
- Material Design Components
- CircleIndicator

### Upcoming Android Integrations
- Hilt
- Retrofit + OkHttp
- Room Database
- DataStore Preferences

### Backend (Planned)
- FastAPI
- PostgreSQL
- SQLAlchemy
- JWT Authentication
- Docker

---

## Project Structure

```text
com.example.railora
├── models
├── repository
├── adapters
├── utils
├── viewmodel
└── ui
    ├── auth
    ├── home
    ├── search
    ├── booking
    ├── wallet
    ├── journeys
    └── profile
```

---

## Current Progress

### Authentication Module
- Splash Screen ✅
- Onboarding Flow (6 Screens) ✅
- Login Screen UI ✅
- Sign Up Screen UI ✅
- Date of Birth Picker ✅
- Login → Sign Up Navigation ✅
- Sign Up → Login Navigation ✅
- Login → Home Navigation ✅
- Sign Up → Home Navigation ✅

### Home Module
- Placeholder Home Screen ✅

---

## Upcoming Features

### Authentication
- Forgot Password Screen
- Form Validation
- Authentication ViewModels
- Backend Integration

### Core Features
- Home Dashboard
- Train Search
- Train Details
- Booking Flow
- Seat Selection
- Journey Management
- Wallet Module
- User Profile

### Backend Integration
- FastAPI APIs
- JWT-based Authentication
- Persistent User Sessions
- Real-time Data Integration

---

## UI Guidelines

- Resource-driven development (no hardcoded values)
- Poppins typography throughout the app
- Figma-inspired interfaces
- Beginner-friendly implementations
- Reusable styles and drawables
- Prefer LinearLayout and RelativeLayout
- Use FrameLayout only when overlays are necessary

---

## Current User Flow

```text
Splash
↓
Onboarding
↓
Login ⇄ Sign Up
↓
Home
```

---

## Developer Notes

Railora is being built incrementally using a UI-first approach, focusing on creating a complete and maintainable user experience before integrating backend services.

The goal is to evolve Railora into a production-ready railway booking application while keeping the codebase simple, structured, and easy to understand.