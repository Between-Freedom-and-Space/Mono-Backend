docker build -t mono_backend .
docker run -d -p 8080:8080 --name mono_backend mono_backend