package com.cc;

import java.util.stream.Stream;

/**
 * Created by xn032607 on 2017/1/13.
 */
public interface Performance {
    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return getMusicians().flatMap(artlist -> Stream.concat(Stream.of(artlist), artlist.getMembers()));
    }
}
