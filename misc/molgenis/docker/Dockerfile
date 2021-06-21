FROM molgenis/commander:1.1.1

RUN yq e '.resources.dataset_folders[0] = "/data/emx"' -i /root/.mcmd/mcmd.yaml && \
    yq e '.resources.dataset_folders[1] = "/data/other"' -i /root/.mcmd/mcmd.yaml && \
    yq e '.resources.resource_folders[0] = "/data/img"' -i /root/.mcmd/mcmd.yaml