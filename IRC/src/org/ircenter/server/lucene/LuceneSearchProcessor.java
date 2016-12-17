package org.ircenter.server.lucene;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * User: Seledkov Kostyantyn
 * Date: 29.05.12
 * Time: 23:08
 */


@Service("luceneSearchProcessor")
public class LuceneSearchProcessor {

    private static final Log LOGGER = LogFactory.getLog(LuceneSearchProcessor.class.getClass());
    public static final int DEFAULT_SEARCH_RESULT_COUNT = 10;
    private static Map<IndexType, Directory> directories = new HashMap<IndexType, Directory>() {{
        put(IndexType.NEWS, new RAMDirectory());
        put(IndexType.MINUTES_TO_GOD, new RAMDirectory());
        put(IndexType.SECRET_SPIRITUAL_WORLD, new RAMDirectory());
        put(IndexType.TV, new RAMDirectory());
        put(IndexType.VIDEO_EVIDENCE, new RAMDirectory());

    }};
    private StandardAnalyzer analyzer;

    public LuceneSearchProcessor() {
    }

    public SearchResult search(String query, IndexType indexType, int hitCount, int offset) {
        List<Long> res = new ArrayList<Long>(hitCount);
        Set<Long> unique = new HashSet<Long>(hitCount);
        int totalHits = 0;
        try {
            IndexSearcher searcher = getIndexSearcher(getReader(indexType));
            TopDocs topDocs = searcher.search(new FuzzyQuery(getTerm(indexType, query)), null, 500);
            totalHits = topDocs.totalHits;
            for (int i = 0; i < topDocs.scoreDocs.length; i++) {
                Document d = searcher.doc(topDocs.scoreDocs[i].doc);
                if (unique.add(Long.parseLong(d.get("id")))) {
                     res.add(Long.parseLong(d.get("id")));
                }   else {
                    totalHits--;
                }
            }
            searcher.close();
        } catch (Exception e) {
        }
        return new SearchResult(indexType, res, totalHits);
    }

    private Term getTerm(IndexType indexType, String query) {
        switch (indexType) {
            case NEWS:
                return new Term("info", query);
            case MINUTES_TO_GOD:
                return new Term("text", query);
            case SECRET_SPIRITUAL_WORLD:
                return new Term("text", query);
            case TV:
                return new Term("title", query);
            case VIDEO_EVIDENCE:
                return new Term("title", query);
            default:
                break;
        }
        return null;
    }

    private String getQuery(IndexType indexType, String query) {
        StringBuilder builder = new StringBuilder();
        switch (indexType) {
            case NEWS:
                //builder.append("info:").append(query);
                builder.append("info:").append(query).append(" OR ").append("title:").append(query);
                break;
            case MINUTES_TO_GOD:
                //builder.append("text:").append(query);
                builder.append("title:").append(query).append(" OR ").append("text:").append(query);
                break;
            case SECRET_SPIRITUAL_WORLD:
                //builder.append("text:").append(query);
                builder.append("title:").append(query).append(" OR ").append("text:").append(query);
                break;
            case TV:
                builder.append("title:").append(query);
                break;
            case VIDEO_EVIDENCE:
                builder.append("title:").append(query);
                break;
            default:
                break;
        }
        return builder.toString();
    }

    public IndexWriter getWriter(IndexType indexType) {
        try {
            return new IndexWriter(directories.get(indexType), new IndexWriterConfig(Version.LUCENE_36, analyzer));
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        return null;
    }

    private IndexReader getReader(IndexType indexType) {
        try {
            return IndexReader.open(directories.get(indexType));
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        return null;
    }

    private IndexSearcher getIndexSearcher(IndexReader indexReader) {
        return new IndexSearcher(indexReader);
    }

    @Autowired
    public void setAnalyzer(StandardAnalyzer analyzer) {
        this.analyzer = analyzer;
    }
}
