#!/bin/bash

# Docker stop script for Technical Assessment

echo "🛑 Stopping Technical Assessment Application..."

# Stop and remove the container
docker stop technical-assessment-app
docker rm technical-assessment-app

echo "✅ Container stopped and removed!"
