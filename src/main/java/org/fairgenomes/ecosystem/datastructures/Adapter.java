package org.fairgenomes.ecosystem.datastructures;

import org.fairgenomes.generator.datastructures.ReleaseType;
import java.time.LocalDate;
import java.util.List;

public class Adapter {

    public String name;
    public String description;
    public Double version;
    public ReleaseType releaseType;
    public LocalDate date;
    public List<Source> source;
    public List<Mapping> mapping;

}
