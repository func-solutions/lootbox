#!/usr/bin/env sh

ssh -L 5432:127.0.0.1:5432 func@kogasa.dedi.c7x.dev -N -v -v &
ssh -L 8587:tower.c7x.dev:8587 func@kogasa.dedi.c7x.dev