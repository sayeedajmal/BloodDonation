# Methods For Authentication

## JWT Authentication

Users authenticate by presenting a JWT token with each request. The token contains information about the user's identity and any associated roles or permissions. Your application verifies the integrity and validity of the token using a JWT validation service (such as your JWTTokenService class) and extracts user information from the token. This approach is stateless and does not require server-side session management.
Includes(JWTConfig, JWTFilter, JWTTokenService)

## Username-Password Authentication

Alternatively, you can still use username-password authentication if needed for certain parts of your application or for specific use cases. In this case, you would typically use an AuthenticationProvider to authenticate users based on their username and password. This approach is stateful and typically involves server-side session management to keep track of authenticated users.
Includes(StaffAuthProvider)

## SCENEARIOS

Break down the steps of the JWT authentication process using the provided classes (`JWTFilter`, `JWTTokenService`, and `JWTConfig`) step by step:

1. **JWTFilter**:

   - Intercepts incoming HTTP requests.
   - Parses the `Authorization` header of the request to extract the JWT token.
   - Validates and extracts the payload from the JWT token.
   - Sets up the authentication context using `SecurityContextHolder` with a `UsernamePasswordAuthenticationToken` containing the username, password (token), and authorities extracted from the token.

2. **JWTAuthProvider**:

   Primary role: Implements Spring Security's AuthenticationProvider interface.

   Functionality:

   - Authenticates users based on the provided credentials (in this case, the JWT token).
   - Trusts the JWTFilter to pre-validate the token.
   - Retrieves user information (if not already done by the filter) based on the extracted user ID.
   - Creates a UsernamePasswordAuthenticationToken object with the user's information and granted authorities for Spring Security's internal use.

3. **JWTTokenService**:
   - Generates a JWT token based on the provided user information.
   - Utilizes the secret key obtained from `JWTConfig` to sign the token.
   - Specifies token expiration and other claims, such as the user's authorities, in the token payload.

Here's the step-by-step flow of the JWT authentication process based on the explanation:

1. The client sends an HTTP request with the JWT token in the `Authorization` header.
2. The `JWTFilter` intercepts the request and extracts the JWT token.
3. The `JWTTokenService` is responsible for token validation and user authentication based on the token.
4. The `JWTTokenService` utilizes the secret key from `JWTConfig` to validate the token's signature and extract user information.
5. If the token is valid, the `JWTFilter` sets up the authentication context using `SecurityContextHolder`, allowing the user to access protected resources.

This process ensures that only authenticated and authorized users can access the application's resources using JWT tokens. Each class plays a specific role in the authentication flow, contributing to the overall security of the application.
