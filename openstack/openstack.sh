#!/bin/bash

# Primero creamos la infrastuctura en OpenStack

#Definición de variables
network="cloudingair-6"
subnetwork="cloudingair-6-subnetwork"
router="clouding-air-6-routing"
securityGroup="lab1"
flavor="lab1"
machine="cloudingair-6-machine"


# Creamos la red
echo "Creating neFtwork $network"
openstack network create $network

# #Creamos la subred
# echo "Creating subnetwork $subnetwork"
# openstack subnet create --subnet-range 10.2.0.0/24 --network $network --dns-nameserver 8.8.8.8 $subnetwork

# Creamos el router
 echo "Creating router $router"
 openstack router create $router

# #Añadimos la subnet al router
 echo "Adding subnetwork $subnetwork to the router $router"
 openstack router add subnet $router $subnetwork

# #Añadimos el gateway al router
 echo "Adding the gateway to the router $router"
 neutron router-gateway-set $router external-network

# #Creamos grupos de seguridad
 echo "Creating security group $securityGroup"
 openstack security group create $securityGroup

# #Creamos la reglas de acceso
 echo "Creating security rules"
 openstack security group rule create --proto icmp $securityGroup
 openstack security group rule create --proto tcp --dst-port 22 $securityGroup
 openstack security group rule create --proto tcp --dst-port 80:80  $securityGroup --remote-ip 0.0.0.0/0
 openstack security group rule create --proto tcp --dst-port 3306  $securityGroup
 openstack security group rule create --proto tcp --dst-port 27017  $securityGroup
 openstack security group rule create --proto tcp --dst-port 5001  $securityGroup

#Creamos el una vm que contega el demonio de Docker
#Hay que buscar la manera de compartir las claves ssh

 docker-machine create --driver openstack --openstack-flavor-name lab1 --openstack-image-name ubuntu-4-docker --openstack-net-name cloudingair-6 --openstack-floatingip-pool external-network --openstack-ssh-user ubuntu --openstack-sec-groups lab1,default --openstack-domain-name Default $machine

# Accedemos a la vm creada
 docker-machine ssh cloudingair-6-machine

# Mapeamos a nuestro terminal
echo "Creating Docker host "
eval $(sudo docker-machine env $machine)

#Inciamos el modo docker swarm
docker swarm init

#Creamos la red overlay
docker network create -d overlay --attachable $network

#Lanzamos los servicios en el clúster creado
docker stack deploy -c docker-compose.yml cloudingair

# Compartir la vm creada con docker-machine con tus compañeros
# docker-machine create \
# --driver generic \
# --generic-ip-address=YOUR_SERVER_IP \
# --generic-ssh-key PATH_TO_YOUR_SSH_KEY \
# my_project