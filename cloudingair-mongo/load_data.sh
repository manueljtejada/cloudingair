#!/bin/bash

mongoimport --host localhost:27018 --db cloudingair src/main/resources/ticket.json --jsonArray
