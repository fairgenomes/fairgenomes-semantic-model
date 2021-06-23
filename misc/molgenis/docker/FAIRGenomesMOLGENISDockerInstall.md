# How to install FAIR Genomes on MOLGENIS Docker

## Command-line tool prerequisites
- Git client (see: https://git-scm.com/downloads)
- Docker client (see: https://docs.docker.com/get-docker)

## Test if everything is installed correctly
`git --version` should return something like `git version 2.8.1`  
`docker --version` should return something like `Docker version 20.10.5`  

## Clone MOLGENIS Docker and FAIR Genomes
```
git clone https://github.com/fairgenomes/fairgenomes-semantic-model.git
```

## Bring MOLGENIS Docker online and load the fair genomes data
- Start MOLGENIS services in docker
```
cd fairgenomes-semantic-model/misc/molgenis/docker
docker-compose up -d
```
- Many files will be imported, to check the logs type:
```
docker-compose logs --follow commander
```
This process should look like
```
✔ Importing sys_md_Package.tsv
✔ Importing study_inclusioncriteria_attributes.tsv
✔ Importing personal_phenotypicsex_attributes.tsv
⠋ Importing personal_genotypicsex_attributes.tsv
```

## All done! 
- Start using FAIR Genomes by going to http://localhost/
- Anonymous users (i.e. not logged in) should already have edit/view rights
- To do and see absolutely everything, log in as `admin`, pw `admin`

## Troubleshooting
To recreate everything, run `nuke.sh`
