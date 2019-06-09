#!/bin/bash

#Definición de variables
network="cloudingair-6"
subnetwork="cloudingair-6-subnetwork"
router="clouding-air-6-routing"
securityGroup="lab1"
flavor="lab1"
machine="cloudingair-6-machine"
ip=$(openstack floating ip list -c "Floating IP Address" -f value)
port=$(openstack port list --router $router -f value | awk '{print $1;}')
dockerNetwork="cloudingair-network"

#Borramos todas las Docker machine localmente
sudo docker-machine rm $(docker-machine ls) --force

#Borramos la red creado para docker swarm
sudo docker network rm $dockerNetwork

# Borramos la ip flotante
openstack floating ip delete $ip

# Borramos  el puerto asiganado al router
openstack router remove port $router $port

# Borramos el router
openstack router delete $router

# Borramos las instancias
openstack server delete $machine

# Borrar las instancias
# for i in `seq 1 2`
#         do
#           openstack server delete tomcat-$i
#         done

# Borrar el grupo de seguridad
openstack security group delete $securityGroup

# Borrar la subred
openstack subnet delete $subnetwork

# Borrar la red
openstack network delete $network
