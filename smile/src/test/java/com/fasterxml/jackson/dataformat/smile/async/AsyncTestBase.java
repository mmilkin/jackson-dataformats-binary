package com.fasterxml.jackson.dataformat.smile.async;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectReadContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.smile.*;

abstract class AsyncTestBase extends BaseTestForSmile
{
    final static String SPACES = "                ";

    protected final static char UNICODE_2BYTES = (char) 167; // law symbol
    protected final static char UNICODE_3BYTES = (char) 0x4567;

    protected final static String UNICODE_SEGMENT = "["+UNICODE_2BYTES+"/"+UNICODE_3BYTES+"]";

    protected AsyncReaderWrapper asyncForBytes(ObjectMapper mapper,
            int bytesPerRead,
            byte[] bytes, int padding) throws IOException
    {
        return asyncForBytes(mapper.reader(), bytesPerRead, bytes, padding);
    }
    
    protected AsyncReaderWrapper asyncForBytes(ObjectReader r,
            int bytesPerRead,
            byte[] bytes, int padding) throws IOException
    {
        return new AsyncReaderWrapperForByteArray(r.createNonBlockingByteArrayParser(),
                bytesPerRead, bytes, padding);
    }

    @Deprecated
    protected AsyncReaderWrapper asyncForBytes(SmileFactory f,
            int bytesPerRead,
            byte[] bytes, int padding) throws IOException
    {
        return new AsyncReaderWrapperForByteArray(f.createNonBlockingByteArrayParser(ObjectReadContext.empty()),
                bytesPerRead, bytes, padding);
    }

    protected static String spaces(int count) 
    {
        return SPACES.substring(0, Math.min(SPACES.length(), count));
    }

    protected final JsonToken verifyStart(AsyncReaderWrapper reader) throws Exception
    {
        assertToken(JsonToken.NOT_AVAILABLE, reader.currentToken());
        return reader.nextToken();
    }
}
