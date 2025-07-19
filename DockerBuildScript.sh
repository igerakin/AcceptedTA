#!/bin/bash

# Build script for Docker image
set -e

echo "Building Technical Assessment Docker image..."

# Build the image
docker build -t technical-assessment:latest .

echo "Build completed successfully!"
echo "To run the container, use: docker run -p 8080:8080 technical-assessment:latest"
