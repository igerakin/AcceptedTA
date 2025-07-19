#!/bin/bash

# Docker build script for Technical Assessment

echo "🚀 Building Technical Assessment Docker Image..."

# Build the Docker image
docker build -t technical-assessment:latest .

# Check if build was successful
if [ $? -eq 0 ]; then
    echo "✅ Docker image built successfully!"
    echo "📦 Image: technical-assessment:latest"
    
    # Show image size
    echo "📏 Image size:"
    docker images technical-assessment:latest --format "table {{.Repository}}\t{{.Tag}}\t{{.Size}}"
else
    echo "❌ Docker build failed!"
    exit 1
fi
