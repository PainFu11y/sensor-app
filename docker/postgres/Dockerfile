FROM postgres:13

COPY ./init.sql /docker-entrypoint-initdb.d/

HEALTHCHECK --interval=10s --timeout=3s --start-period=10s --retries=30 CMD pg_isready -U postgres -d platform_order || exit 1
