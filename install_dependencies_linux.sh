#!/bin/bash

echo "--- Updating Before Install ----------------------------------------------------"
sudo apt update

echo "--- Installing Backend Dependencies --------------------------------------------"

sudo apt install \
    openjdk-11-jdk \
    maven
