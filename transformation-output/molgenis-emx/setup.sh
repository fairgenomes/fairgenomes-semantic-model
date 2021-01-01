mcmd import -p sys_md_Package.tsv
mcmd import -p personal_lookup_gender_attributes.tsv --as attributes --in fair-genomes
mcmd import -p personal_lookup_genotypicsex_attributes.tsv --as attributes --in fair-genomes
mcmd import -p personal_lookup_countryofresidence_attributes.tsv --as attributes --in fair-genomes
mcmd import -p personal_lookup_gender.tsv --as fair-genomes_personal_lookup_gender --in fair-genomes
mcmd import -p personal_lookup_genotypicsex.tsv --as fair-genomes_personal_lookup_genotypicsex --in fair-genomes
mcmd import -p personal_lookup_countryofresidence.tsv --as fair-genomes_personal_lookup_countryofresidence --in fair-genomes
