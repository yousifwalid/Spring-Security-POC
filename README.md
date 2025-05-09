# Spring-Security-POC

This is a minimal **Proof of Concept (PoC)** showcasing how to implement authentication and authorization using **Spring Security** with **JSON Web Tokens (JWT)** in a service-layer architecture.

The goal is to demonstrate how secure endpoints can be protected and how tokens can be issued, validated, and used to authorize requests in a stateless REST API.

 Key Features:
----------------
- Stateless Authentication using **JWT**
- Login endpoint that issues a token after credential validation
- Secure protected endpoints with role-based access control
- Token validation using `OncePerRequestFilter`
- Easily extendable for real-world production use
