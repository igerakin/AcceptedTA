#!/bin/bash

# Run script for Docker container
set -e

echo "Starting Technical Assessment container..."

# Stop and remove existing container if running
docker stop technical-assessment-app 2>/dev/null || true
docker rm technical-assessment-app 2>/dev/null || true

# Run the container
docker run -d \
  --name technical-assessment-app \
  -p 8080:8080 \
  --health-cmd="curl -f http://localhost:8080/actuator/health || exit 1" \
  --health-interval=30s \
  --health-timeout=10s \
  --health-retries=3 \
  technical-assessment:latest

echo "Container started successfully!"
echo "API available at: http://localhost:8080/api"
echo "Swagger UI available at: http://localhost:8080/swagger-ui.html"
echo "H2 Console available at: http://localhost:8080/h2-console"
echo ""
echo "To check container status: docker ps"
echo "To view logs: docker logs technical-assessment-app"
echo "To stop container: docker stop technical-assessment-app"
