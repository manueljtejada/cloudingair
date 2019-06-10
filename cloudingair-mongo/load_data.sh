#!/bin/bash

mongoimport --host localhost:27018 --db cloudingair src/main/resources/ticket.json --jsonArray

db.ticket.aggregate([
  {$unwind:'$purchases'},
  {"$project": { "purchases.store": 1 , "distinctPassenger": "$id", "year": { "$year" : [ "$purchases.purchase.amount"]} , "total" : { "$reduce" : { "input" : "$spend" , "initialValue" : 0 , "in" : { "$add" : [ "$$value" , "$$this"]}}}}} , { "$match" : { "$and" : [ { "store" : "yg51"} , { "year" : 2018}]}} , { "$group" : { "_id" : { "store" : "$store" , "year" : "$year"} , "total" : { "$sum" : "$total"}}}])

db.ticket.aggregate([
  {$unwind:'$purchases'},
  {$project:{_id:1,'purchase.store.name':"$purchases.purchase.name", "year": {  }}},

])

db.ticket.aggregate([{ $project: { "boarding.gate": 1, year: { $year: "$boarding.timestamp" }, dateDifference: { $divide: [{ $subtract: ["$boarding.timestamp", "$securityCheck.timestamp"] }, 60000] } } }, { $group: { _id: { year: "$year", gate: "$boarding.gate" }, avgtime: { $avg: "$dateDifference" } } }])
